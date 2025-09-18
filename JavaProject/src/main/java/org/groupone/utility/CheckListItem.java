package org.groupone.utility;

import org.groupone.DTO.RUserDTO;

public class CheckListItem {
    private RUserDTO user;
    private boolean selected;

    public CheckListItem(RUserDTO user) {
        this.user = user;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

    }

    public RUserDTO getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.getName()+" \t " +user.getLastname();
    }





}
