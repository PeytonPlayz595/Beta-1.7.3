package net.lax1dude.eaglercraft.beta;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.WorldInfo;

public class SingleplayerCommands {
	
	public static final HashMap<String, Command> singleplayerCommands;
	
	static {
		singleplayerCommands = new HashMap();
		singleplayerCommands.put("help", new CommandHelp());
		singleplayerCommands.put("give", new CommandGiveItem());
		singleplayerCommands.put("summon", new CommandSummon());
		singleplayerCommands.put("time", new CommandTime());
	}
	
	public static interface Command {
		void processCommand(Minecraft mc, String[] args) throws Throwable;
		String getDescription();
	}
	
	public static class CommandException extends RuntimeException {
		public CommandException() { super(); }
		public CommandException(String message, Throwable cause) { super(message, cause); }
		public CommandException(String message) { super(message); }
	}

	public static void processCommand(Minecraft mc, String cmd) {
		String[] args = cmd.split("\\s+");
		if(args.length > 0) {
			Command command = singleplayerCommands.get(args[0]);
			if(command != null) {
				String[] passArgs = new String[args.length - 1];
				System.arraycopy(args, 1, passArgs, 0, passArgs.length);
				try {
					command.processCommand(mc, passArgs);
				}catch(CommandException t) {
					mc.displayErrorChat("Command Error: " + FontRenderer.formatChar + "r" + t.getMessage() + (t.getCause() != null ? " (caused by: " + t.getCause().toString() + ")" : ""));
					//t.printStackTrace();
				}catch(Throwable t) {
					mc.displayErrorChat("Command Error: " + FontRenderer.formatChar + "r" + t.toString());
					//t.printStackTrace();
				}
			}else {
				mc.displayErrorChat("Command '" + args[0] + "' does not exist");
			}
		}
	}
	
	public static class CommandHelp implements Command {

		@Override
		public void processCommand(Minecraft mc, String[] args) {
			mc.displayChat(FontRenderer.formatChar + "aAvailable Singleplayer Commands:");
			for(Entry<String, Command> cmd : singleplayerCommands.entrySet()) {
				mc.displayChat(" " + FontRenderer.formatChar + "e/" + cmd.getKey() + FontRenderer.formatChar + "r - " + cmd.getValue().getDescription());
			}
		}

		@Override
		public String getDescription() {
			return "list all available commands";
		}
		
	}
	
	public static class CommandGiveItem implements Command {

		@Override
		public void processCommand(Minecraft mc, String[] args) throws Throwable {
			if(args.length != 1 && args.length != 2 && args.length != 3) {
				throw new CommandException("arguments must be: <id> [count] [damage]");
			}
			int id = Integer.parseInt(args[0]);
			boolean exists = id < 256 ? Block.blocksList[id] != null : Item.itemsList[id] != null;
			if(exists) {
				ItemStack stack = new ItemStack(id, args.length == 2 ? Integer.parseInt(args[1]) : 1, args.length == 3 ? Integer.parseInt(args[2]) : 0);
				mc.thePlayer.inventory.addItemStackToInventory(stack);
				if(id < 256) {
					mc.displayChat("Gave player block '" + id + "'");
				}else {
					mc.displayChat("Gave player item '" + id + "'");
				}
			}else {
				mc.displayErrorChat("Item/Block id #" + id + " does not exist!");
			}
		}

		@Override
		public String getDescription() {
			return "give item <id> [count]";
		}
		
	}
	
	public static class CommandSummon implements Command {

		@Override
		public void processCommand(Minecraft mc, String[] args) throws Throwable {
			if(args.length != 1 && args.length != 4) {
				throw new CommandException("arguments must be: <name> [x] [y] [z]");
			}
			Entity e;
			try {
				int id = Integer.parseInt(args[0]);
				if(id == 1 || id == 49 || id == 9) {
					throw new CommandException("Entity id '" + args[0] + "' is not registered");
				}
				e = EntityList.createEntity(id, mc.theWorld);
			}catch(NumberFormatException ex) {
				String s = args[0].toLowerCase();
				if(s.equals("item") || s.equals("monster") || s.equals("painting")) {
					throw new CommandException("Entity id '" + args[0] + "' is not registered");
				}
				e = EntityList.createEntityInWorld(s, mc.theWorld);
			}
			if(e == null) {
				throw new CommandException("Entity id '" + args[0] + "' is not registered");
			}
			int x, y, z;
			if(args.length == 4) {
				x = Integer.parseInt(args[1]);
				y = Integer.parseInt(args[2]);
				z = Integer.parseInt(args[3]);
				while(y > 0 && !mc.theWorld.isBlockOpaqueCube(x, y - 1, z)) {
					--y;
				}
			}else {
				x = MathHelper.floor_double(mc.thePlayer.posX);
				y = MathHelper.floor_double(mc.thePlayer.posY);
				z = MathHelper.floor_double(mc.thePlayer.posZ);
			}
			e.setLocationAndAngles(x, y, z, 0.0f, 0.0f);
			mc.theWorld.entityJoinedWorld(e);
		}

		@Override
		public String getDescription() {
			return "spawn an entity <id> [x] [y] [z]";
		}
		
	}
	
	public static class CommandTime implements Command {

		@Override
		public void processCommand(Minecraft mc, String[] args) throws Throwable {
			int i;
			if(args.length != 1) {
				throw new CommandException("arguments must be: <ticks>");
			}
			try {
				i = Integer.parseInt(args[0]);
			}catch(NumberFormatException ex) {
				throw new CommandException("time argument must be an integer");
			}
			WorldInfo inf = mc.theWorld.getWorldInfo();
			long t = inf.getWorldTime();
			t = t / 24000l * 24000l;
			t += i;
			inf.setWorldTime(t);
			mc.displayChat("Set world time to " + i + " ticks");
		}

		@Override
		public String getDescription() {
			return "set world time in <ticks>";
		}
		
	}
	
}