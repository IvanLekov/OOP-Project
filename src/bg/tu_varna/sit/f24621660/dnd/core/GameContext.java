package bg.tu_varna.sit.f24621660.dnd.core;

public class GameContext {
    private boolean isFileOpen = false;
    private String currentFileName = null;
    // Тук по-късно ще добавиш:
    // - Картата на играта
    // - Данните за героя
    // - Списък с врагове и т.н.

    public boolean isFileOpen() { return isFileOpen; }
    public void setFileOpen(boolean fileOpen) { isFileOpen = fileOpen; }

    public String getCurrentFileName() { return currentFileName; }
    public void setCurrentFileName(String fileName) { this.currentFileName = fileName; }
}