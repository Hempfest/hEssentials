package org.spigotmc.hessentials.util;

import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CommandsRegistrar {

    private static void registerCommand(BukkitCommand command) {
        try {

            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(command.getLabel(), command);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerCommands(String packageName, Plugin plugin) {
        for (Class<?> aClass : getCommandExecutorsInPackage(packageName, plugin)) {
            try {
                registerCommand((BukkitCommand) aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static Set<Class<?>> getCommandExecutorsInPackage(String packageName, Plugin plugin) {
        Set<Class<?>> classes = Sets.newHashSet();
        JarFile jarFile;
        try {
            jarFile = new JarFile(URLDecoder.decode(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().getFile(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return classes;
        }
        for (JarEntry jarEntry : Collections.list(jarFile.entries())) {
            String className = jarEntry.getName().replace("/", ".");
            if (className.startsWith(packageName) && className.endsWith(".class")) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(className.substring(0, className.length() - 6));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    continue;
                }
                if (BukkitCommand.class.isAssignableFrom(clazz)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }
}

