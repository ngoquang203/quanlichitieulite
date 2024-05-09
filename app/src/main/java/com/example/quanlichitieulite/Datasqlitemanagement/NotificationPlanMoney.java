package com.example.quanlichitieulite.Datasqlitemanagement;

public class NotificationPlanMoney {
    private String nameService,dateStart,dateEnd;

    public NotificationPlanMoney(String nameService, String dateStart, String dateEnd) {
        this.nameService = nameService;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
