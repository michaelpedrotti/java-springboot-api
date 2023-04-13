package xyz.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "permission")
@Entity(name="PermissionEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PermissionEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="profile_id")
    private Long profileId;

    private String resource;

    private String actions = "[]";


    public PermissionEntity setActions(ArrayList<String> actions){

        this.actions = new Gson().toJson(actions);

        return this;
    }

    public ArrayList<String> getActions(){

        return new Gson().fromJson(this.actions, new TypeToken<List<String>>(){}.getType());
    }
}