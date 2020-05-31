package application;

import java.util.ArrayList;

public class DetailsDto {

    private ArrayList<String> perks;
    private ArrayList<String> fragments;

    public DetailsDto() {
    }

    public DetailsDto(ArrayList<String> perks, ArrayList<String> fragments) {
        this.perks = perks;
        this.fragments = fragments;
    }

    public ArrayList<String> getPerks() {
        return perks;
    }

    public void setPerks(ArrayList<String> perks) {
        this.perks = perks;
    }

    public ArrayList<String> getFragments() {
        return fragments;
    }

    public void setFragments(ArrayList<String> fragments) {
        this.fragments = fragments;
    }
}
