package org.spigotmc.hessentials.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Material;

public final class MaterialUtils
{
    private static final Map<String, Material> MATERIAL_ALIAS = new HashMap<>();

    static
    {
        for (Material material : Material.values())
        {
            MATERIAL_ALIAS.put(material.name().toLowerCase().replace("_", ""), material);
        }
    }

    @Nullable
    public static Material getMaterial(@Nonnull String name)
    {
        return MATERIAL_ALIAS.get(name.toLowerCase().replaceAll("_", ""));
    }

    private MaterialUtils() { }
}
