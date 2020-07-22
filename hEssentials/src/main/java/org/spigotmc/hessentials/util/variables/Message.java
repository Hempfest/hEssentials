package org.spigotmc.hessentials.util.variables;

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
	@SuppressWarnings("deprecation")
	public static void textHoverable(CommandSender sender, String textBody, String hoverTextBody, String hoverMessage) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	@SuppressWarnings("deprecation")
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
	@SuppressWarnings("deprecation")
	public static void textHoverable(CommandSender sender, String textBody, String hoverTextBody, String textBody2, String hoverTextbody2, String hoverMessage, String hoverMessage2) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		TextComponent text2 = new TextComponent(Strings.color(textBody2));
		TextComponent hover2 = new TextComponent(Strings.color(hoverTextbody2));
		text.addExtra(hover);
		text.addExtra(text2);
		text.addExtra(hover2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage2))).create()));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
		// *
		//
		// SEND TEXT THE PLAYER CAN BE SUGGESTED TO EXECUTE COMMANDS WITH
		//
	@SuppressWarnings("deprecation")
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
	@SuppressWarnings("deprecation")
	public static void textRunnable(CommandSender sender, String textBody, String hoverTextBody, String hoverMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	@SuppressWarnings("deprecation")
	public static void textRunnable(CommandSender sender, String textBody, String hoverTextBody, String textBody2, String hoverMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		TextComponent text2 = new TextComponent(Strings.color(textBody2));
		text.addExtra(hover);
		text.addExtra(text2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	@SuppressWarnings("deprecation")
	public static void textRunnable(CommandSender sender, String textBody, String hoverTextBody, String textBody2, String hoverTextbody2, String hoverMessage, String hoverMessage2, String commandName, String commandName2) {
		TextComponent text = new TextComponent(Strings.color(textBody));
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		TextComponent text2 = new TextComponent(Strings.color(textBody2));
		TextComponent hover2 = new TextComponent(Strings.color(hoverTextbody2));
		text.addExtra(hover);
		text.addExtra(text2);
		text.addExtra(hover2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage2))).create()));
		hover2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName2));
		sender.spigot().sendMessage((BaseComponent)text); 
		return;
	}
	@SuppressWarnings("deprecation")
	public static void textRunnable(CommandSender sender, String hoverTextBody, String hoverTextBody2, String hoverTextBody3, String hoverMessage, String hoverMessage2, String hoverMessage3, String commandName, String commandName2, String commandName3) {
		TextComponent hover = new TextComponent(Strings.color(hoverTextBody));
		TextComponent hover2 = new TextComponent(Strings.color(hoverTextBody2));
		TextComponent hover3 = new TextComponent(Strings.color(hoverTextBody3));
		hover.addExtra(hover2);
		hover.addExtra(hover3);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage))).create()));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage2))).create()));
		hover2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName2));
		hover3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(Strings.color(hoverMessage3))).create()));
		hover3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName3));
		sender.spigot().sendMessage((BaseComponent)hover); 
		return;
	}
	
	

}
