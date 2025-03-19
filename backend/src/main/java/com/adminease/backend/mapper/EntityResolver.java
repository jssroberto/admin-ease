package com.adminease.backend.mapper;

import com.adminease.backend.model.Area;
import com.adminease.backend.model.Insumo;
import com.adminease.backend.model.Usuario;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class EntityResolver {

    // Convert ID to Area proxy (avovers database lookup in mapper)
    @Named("idToArea")
    public Area idToArea(Long id) {
        if (id == null) return null;
        Area area = new Area();
        area.setId(id);
        return area;
    }

    @Named("idToUsuario")
    public Usuario idToUsuario(Long id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    @Named("idToInsumo")
    public Insumo idToInsumo(Long id) {
        if (id == null) return null;
        Insumo insumo = new Insumo();
        insumo.setId(id);
        return insumo;
    }
}