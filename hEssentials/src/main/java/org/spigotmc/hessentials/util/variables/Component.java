package org.spigotmc.hessentials.util.variables;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class Component {


		// *
		//
		// SEND TEXT THE PLAYER CAN ONLY HOVER OVER AND SEE MORE TEXT WITH
		//
	
	public static TextComponent textHoverable(CommandSender sender, String normalText, String hoverText, String hoverTextMessage) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		return text;
	}
	
	public static TextComponent textHoverable(CommandSender sender, String normalText, String hoverText, String normalText2, String hoverTextMessage) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		TextComponent text2 = new TextComponent(Strings.color(normalText2));
		text.addExtra(hover);
		text.addExtra(text2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage))))); 
		return text;
	}
	
	public static TextComponent textHoverable(CommandSender sender, String normalText, String hoverText, String normalText2, String hoverText2, String hoverTextMessage, String hoverText2Message) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		TextComponent text2 = new TextComponent(Strings.color(normalText2));
		TextComponent hover2 = new TextComponent(Strings.color(hoverText2));
		text.addExtra(hover);
		text.addExtra(text2);
		text.addExtra(hover2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverText2Message))))); 
		return text;
	}
		// *
		//
		// SEND TEXT THE PLAYER CAN BE SUGGESTED TO EXECUTE COMMANDS WITH
		//
	
	public static TextComponent textSuggestable(CommandSender sender, String normalText, String hoverText, String hoverTextMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + commandName));
		return text;
	}
		// *
		//
		// SEND TEXT THE PLAYER CAN RUN COMMANDS WITH
		//
	
	public static TextComponent textRunnable(CommandSender sender, String normalText, String hoverText, String hoverTextMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		text.addExtra(hover);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName)); 
		return text;
	}
	
	public static TextComponent textRunnable(CommandSender sender, String normalText, String hoverText, String normalText2, String hoverTextMessage, String commandName) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		TextComponent text2 = new TextComponent(Strings.color(normalText2));
		text.addExtra(hover);
		text.addExtra(text2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName)); 
		return text;
	}
	
	public static TextComponent textRunnable(CommandSender sender, String normalText, String hoverText, String normalText2, String hoverText2, String hoverTextMessage, String hoverText2Message, String commandName, String commandName2) {
		TextComponent text = new TextComponent(Strings.color(normalText));
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		TextComponent text2 = new TextComponent(Strings.color(normalText2));
		TextComponent hover2 = new TextComponent(Strings.color(hoverText2));
		text.addExtra(hover);
		text.addExtra(text2);
		text.addExtra(hover2);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverText2Message)))));
		hover2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName2));
		return text;
	}
	
	public static TextComponent textRunnable(CommandSender sender, String hoverText, String hoverText2, String hoverTextBody3, String hoverTextMessage, String hoverText2Message, String hoverMessage3, String commandName, String commandName2, String commandName3) {
		TextComponent hover = new TextComponent(Strings.color(hoverText));
		TextComponent hover2 = new TextComponent(Strings.color(hoverText2));
		TextComponent hover3 = new TextComponent(Strings.color(hoverTextBody3));
		hover.addExtra(hover2);
		hover.addExtra(hover3);
		hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverTextMessage)))));
		hover.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName));
		hover2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverText2Message)))));
		hover2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName2));
		hover3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new Text(Strings.color(hoverMessage3)))));
		hover3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + commandName3));
		return hover;
	}
	
	

}
