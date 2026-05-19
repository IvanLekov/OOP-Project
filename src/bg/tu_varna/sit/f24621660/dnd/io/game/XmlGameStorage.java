package bg.tu_varna.sit.f24621660.dnd.io.game;

import bg.tu_varna.sit.f24621660.dnd.core.GameContext;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.HeroFactory;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.GameMap;
import bg.tu_varna.sit.f24621660.dnd.world.models.map.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlGameStorage implements GameStorage {

    @Override
    public void save(GameContext context, String fileName) throws Exception {
        if (context.getHero() == null || context.getCurrentLevel() == null) {
            throw new IllegalStateException("No active game to save.");
        }

        Hero hero = context.getHero();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // --- 1. КОРЕН НА ФАЙЛА ---
        Element rootElement = doc.createElement("game_save");
        doc.appendChild(rootElement);

        // --- 2. СЪСТОЯНИЕ НА ИГРАТА (State) ---
        Element stateElement = doc.createElement("state");
        stateElement.appendChild(doc.createTextNode(context.getStateManager().getCurrent().name()));
        rootElement.appendChild(stateElement);

        // --- 3. ДАННИ ЗА КАРТАТА И ПОЗИЦИЯТА ---
        Element mapElement = doc.createElement("map_data");
        mapElement.setAttribute("level_index", String.valueOf(hero.getLevel().getValue()));

        Position heroPos = context.getCurrentLevel().mapManager().getHeroPosition();
        mapElement.setAttribute("hero_x", String.valueOf(heroPos.x()));
        mapElement.setAttribute("hero_y", String.valueOf(heroPos.y()));
        rootElement.appendChild(mapElement);

        // --- ДОБАВЕНО: ЗАПИС НА САМАТА МАТРИЦА (СЪСТОЯНИЕТО НА СВЕТА) ---
        Element layoutElement = doc.createElement("map_layout");
        GameMap map = context.getCurrentLevel().map();
        for (int r = 0; r < map.getRowsCount(); r++) {
            Element rowElement = doc.createElement("row");
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < map.getColsCount(); c++) {
                sb.append(map.getCell(new Position(r, c)));
            }
            rowElement.appendChild(doc.createTextNode(sb.toString()));
            layoutElement.appendChild(rowElement);
        }
        rootElement.appendChild(layoutElement);

        // --- 4. ГЕРОЙ И СТАТИСТИКИ ---
        Element heroElement = doc.createElement("hero");
        rootElement.appendChild(heroElement);

        Element levelElement = doc.createElement("level");
        levelElement.appendChild(doc.createTextNode(String.valueOf(hero.getLevel().getValue())));
        heroElement.appendChild(levelElement);

        Element hpElement = doc.createElement("hp");
        hpElement.setAttribute("current", String.valueOf(hero.getHealth().getValue()));
        hpElement.setAttribute("max", String.valueOf(hero.getHealth().getMaxValue()));
        heroElement.appendChild(hpElement);

        Element strElement = doc.createElement("str");
        strElement.appendChild(doc.createTextNode(String.valueOf(hero.getStrength().getValue())));
        heroElement.appendChild(strElement);

        Element manaElement = doc.createElement("mana");
        manaElement.appendChild(doc.createTextNode(String.valueOf(hero.getMana().getValue())));
        heroElement.appendChild(manaElement);

        // --- 5. ЕКИПИРОВКА ---
        Element eqElement = doc.createElement("equipment");
        heroElement.appendChild(eqElement);

        if (hero.getWeapon() != null) {
            Element wpnElement = doc.createElement("weapon");
            wpnElement.setAttribute("name", hero.getWeapon().getName());
            wpnElement.setAttribute("amp", String.valueOf(hero.getWeapon().getDamageIncrease()));
            eqElement.appendChild(wpnElement);
        }

        if (hero.getSpell() != null) {
            Element splElement = doc.createElement("spell");
            splElement.setAttribute("name", hero.getSpell().getName());
            splElement.setAttribute("amp", String.valueOf(hero.getSpell().getDamageIncrease()));
            eqElement.appendChild(splElement);
        }

        if (hero.getArmor() != null) {
            Element armElement = doc.createElement("armor");
            armElement.setAttribute("name", hero.getArmor().getName());
            armElement.setAttribute("reduction", String.valueOf(hero.getArmor().getDamageReduction()));
            eqElement.appendChild(armElement);
        }

        // --- 6. ЗАПИСВАНЕ НА ФАЙЛА ---
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    @Override
    public void load(GameContext context, String fileName) throws Exception {
        File xmlFile = new File(fileName);
        if (!xmlFile.exists()) {
            throw new IllegalArgumentException("Save file not found: " + fileName);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // 1. Възстановяване на State
        String stateStr = doc.getElementsByTagName("state").item(0).getTextContent();
        context.getStateManager().changeTo(State.valueOf(stateStr));

        // 2. Четене на метаданните за картата
        Element mapElement = (Element) doc.getElementsByTagName("map_data").item(0);
        int levelIndex = Integer.parseInt(mapElement.getAttribute("level_index"));
        int heroX = Integer.parseInt(mapElement.getAttribute("hero_x"));
        int heroY = Integer.parseInt(mapElement.getAttribute("hero_y"));
        Position loadedPosition = new Position(heroX, heroY);

        // --- ДОБАВЕНО: ЧЕТЕНЕ НА МАТРИЦАТА ОТ XML ---
        List<String> mapRows = new ArrayList<>();
        NodeList rowNodes = doc.getElementsByTagName("row");
        for (int i = 0; i < rowNodes.getLength(); i++) {
            mapRows.add(rowNodes.item(i).getTextContent());
        }

        // 3. Четене на статистики
        int level = Integer.parseInt(doc.getElementsByTagName("level").item(0).getTextContent());
        Element hpAttr = (Element) doc.getElementsByTagName("hp").item(0);
        int currentHp = Integer.parseInt(hpAttr.getAttribute("current"));
        int maxHp = Integer.parseInt(hpAttr.getAttribute("max"));
        int str = Integer.parseInt(doc.getElementsByTagName("str").item(0).getTextContent());
        int mana = Integer.parseInt(doc.getElementsByTagName("mana").item(0).getTextContent());

        // 4. Четене на екипировката
        Weapon weapon = null;
        Spell spell = null;
        DefensiveItem armor = null;

        Element eqElement = (Element) doc.getElementsByTagName("equipment").item(0);

        if (eqElement.getElementsByTagName("weapon").getLength() > 0) {
            Element wElement = (Element) eqElement.getElementsByTagName("weapon").item(0);
            weapon = new Weapon(wElement.getAttribute("name"), Double.parseDouble(wElement.getAttribute("amp")));
        }
        if (eqElement.getElementsByTagName("spell").getLength() > 0) {
            Element sElement = (Element) eqElement.getElementsByTagName("spell").item(0);
            spell = new Spell(sElement.getAttribute("name"), Double.parseDouble(sElement.getAttribute("amp")));
        }
        if (eqElement.getElementsByTagName("armor").getLength() > 0) {
            Element aElement = (Element) eqElement.getElementsByTagName("armor").item(0);
            armor = new Armor(aElement.getAttribute("name"), Double.parseDouble(aElement.getAttribute("reduction")));
        }

        // 5. Съживяване на героя
        Hero loadedHero = HeroFactory.recreateHero(currentHp, maxHp, str, mana, level, weapon, spell, armor);
        context.setHero(loadedHero);

        // 6. Подаваме пълните данни (включително редовете на картата) към контекста
        context.setSavedMapData(levelIndex, loadedPosition, mapRows);
    }
}