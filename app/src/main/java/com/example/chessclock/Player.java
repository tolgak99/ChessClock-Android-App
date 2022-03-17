package com.example.chessclock;

public class Player {
    private int mainHour = 0;
    private int mainMinute = 0;
    private int mainSecond = 0;

    private int mainHourTemp = 0;
    private int mainMinuteTemp = 0;
    private int mainSecondTemp = 0;

    void Player()
    {
        this.mainHour = 0;
        this.mainMinute = 10;
        this.mainSecond = 0;

        mainHourTemp = mainHour;
        mainMinuteTemp = mainMinute;
        mainSecondTemp = mainSecond;
    }

    void PLayer(int hour, int minute, int second)
    {
        this.mainHour = hour;
        this.mainMinute = minute;
        this.mainSecond = second;

        mainHourTemp = mainHour;
        mainMinuteTemp = mainMinute;
        mainSecondTemp = mainSecond;
    }

    public int getMainHour() {
        return mainHour;
    }

    public void setMainHour(int mainHour) {
        this.mainHour = mainHour;
    }

    public int getMainMinute() {
        return mainMinute;
    }

    public void setMainMinute(int mainMinute) {
        this.mainMinute = mainMinute;
    }

    public int getMainSecond() {
        return mainSecond;
    }

    public void setMainSecond(int mainSecond) {
        this.mainSecond = mainSecond;
    }

    public int getMainHourTemp() {
        return mainHourTemp;
    }

    public void setMainHourTemp(int mainHourTemp) {
        this.mainHourTemp = mainHourTemp;
    }

    public int getMainMinuteTemp() {
        return mainMinuteTemp;
    }

    public void setMainMinuteTemp(int mainMinuteTemp) {
        this.mainMinuteTemp = mainMinuteTemp;
    }

    public int getMainSecondTemp() {
        return mainSecondTemp;
    }

    public void setMainSecondTemp(int mainSecondTemp) {
        this.mainSecondTemp = mainSecondTemp;
    }
}
