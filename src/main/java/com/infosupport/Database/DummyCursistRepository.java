package com.infosupport.Database;

import com.infosupport.domain.Cursist;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
@Data
public class DummyCursistRepository {

    List<Cursist> cursists;

    public DummyCursistRepository() {
        cursists = new ArrayList<>();
    }

    public void create(Cursist cursist) {
        cursists.add(cursist);
    }

    public Cursist getCursist(int id) {
        return cursists.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
