package models;

import java.util.List;

/**
 * Created by Juan Lucena on 21/01/2017.
 */

public class ResultResponse {

    private List<Film> items;

    public List<Film> getResults() {
        return items;
    }

    public void setResults(List<Film> results) {
        this.items = results;
    }
}
