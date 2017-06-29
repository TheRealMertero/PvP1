package de.mertero.pvp.commands;



import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CMD_friede implements CommandExecutor {
	
	//private ArrayList<String> friedewait = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
					
					
					/*if(sender instanceof Player) {
						Player p = (Player)sender;
						if(cmd.getName().equalsIgnoreCase("friede")) {
							if(args.length == 1){
								for(Player all : Bukkit.getOnlinePlayers()){
									if(args[0].equals(all.getName())){
										if(all.getName() != null) {
											if(!Main.getInstance().getFriede().contains(all.getName())){
												all.sendMessage(Main.getInstance().getPrefix() + "§a" + p.getName() + "§7 Möchte mit dir Friede schließen!");
												friedewait.add(all.getName());
												p.sendMessage(Main.getInstance().getPrefix() + "Du hast " + all.getName() + " eine Friedensanfrage gesendet");

											} else {
												p.sendMessage(Main.getInstance().getPrefix() + " Du hast mit diesem Spieler schon Friede geschlossen!");
											}
										}
									}
								}
							} else if(args.length == 2) {
								for(Player all : Bukkit.getOnlinePlayers()){
									if(args[0].equalsIgnoreCase("remove")){
										if(args[1].equals(all.getName())){
											if(Main.getInstance().getFriede().contains(all.getName())){
											Main.getInstance().getFriede().remove(all.getName());
											all.sendMessage(Main.getInstance().getPrefix() + p.getName() + " möchte kein Friede mehr!");
											} else {
												p.sendMessage(Main.getInstance().getPrefix() + " Du hast mit diesem Spieler kein Friede!");
											}
										}
									} else if(args[0].equalsIgnoreCase("accept")){
										if(args[1].equals(all.getName())){ 
											if(friedewait.contains(all.getName())){
												Main.getInstance().getFriede().add(all.getName());
												friedewait.remove(all.getName());
												p.sendMessage(Main.getInstance().getPrefix() + "Du hast jetzt mit " + all.getName() + " Friede");
												all.sendMessage(Main.getInstance().getPrefix() + p.getName() + " hat deine Anfrage angenommen!");
											}
										}
									}
								}
							}
						}
					}
		
		
		*/
		return false;
	}

}
