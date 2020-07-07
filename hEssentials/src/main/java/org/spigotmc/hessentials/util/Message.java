package org.spigotmc.hessentials.util;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Message {


		// *
		//
		// SEND TEXT THE PLAYER CAN ONLY HOVER OVER AND SEE MORE TEXT WITH
		//
	public static void textHoverable(CommandSender sender, String textBody, String hoverTextBody, String hoverMessage) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	public static void textHoverable(CommandSender sender, String textBody, String hoverTextBody, String textBody2, String hoverMessage) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		TextComponent text2 = new TextComponent(Strings.color(textBody2));
		text.addExtra(hover);
		text.addExtra(text2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
		// *
		//
		// SEND TEXT THE PLAYER CAN BE SUGGESTED TO EXECUTE COMMANDS WITH
		//
	public static void textSuggestable(CommandSender sender, String textBody, String hoverTextBody, String hoverMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + commandName));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
		// *
		//
		// SEND TEXT THE PLAYER CAN RUN COMMANDS WITH
		//
	public static void textRunnable(CommandSender sender, String textBody, String hoverTextBody, String hoverMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	
	

}
