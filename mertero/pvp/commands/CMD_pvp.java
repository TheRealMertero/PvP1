package de.mertero.pvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mertero.pvp.main.Main;


public class CMD_pvp implements CommandExecutor {

	 @SuppressWarnings("deprecation")
	    @Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	        if(cmd.getName().equalsIgnoreCase("pvp")){
	            if(sender instanceof Player){
	                Player p = (Player) sender;
	                p.sendTitle("§6P", "");
	                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                   
	                    @Override
	                    public void run() {
	                        p.sendTitle("§9Pv", "");
	                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                           
	                            @Override
	                            public void run() {
	                                p.sendTitle("§1PvP", "");
	                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                   
	                                    @Override
	                                    public void run() {
	                                        p.sendTitle("§5PvP", "");
	                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                           
	                                            @Override
	                                            public void run() {
	                                                p.sendTitle("§bPvP", "");
	                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                   
	                                                    @Override
	                                                    public void run() {
	                                                        p.sendTitle("§4PvP", "");
	                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                           
	                                                            @Override
	                                                            public void run() {
	                                                               
	                                                                p.sendTitle("§4PvP", "§§8D");
	                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                   
	                                                                    @Override
	                                                                    public void run() {
	                                                                        p.sendTitle("§4PvP", "§8De");
	                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                           
	                                                                            @Override
	                                                                            public void run() {
	                                                                                p.sendTitle("§4PvP", "§8Dev");
	                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                   
	                                                                                    @Override
	                                                                                    public void run() {
	                                                                                        p.sendTitle("§4PvP", "§8Deve");
	                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                           
	                                                                                            @Override
	                                                                                            public void run() {
	                                                                                                p.sendTitle("§4PvP", "§8Devel");
	                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                   
	                                                                                                    @Override
	                                                                                                    public void run() {
	                                                                                                        p.sendTitle("§4PvP", "§8Develo");
	                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                           
	                                                                                                            @Override
	                                                                                                            public void run() {
	                                                                                                                p.sendTitle("§4PvP", "§8Develop");
	                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                   
	                                                                                                                    @Override
	                                                                                                                    public void run() {
	                                                                                                                        p.sendTitle("§4PvP", "§8Develope");
	                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                           
	                                                                                                                            @Override
	                                                                                                                            public void run() {
	                                                                                                                                p.sendTitle("§4PvP", "§8Developed");
	                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                   
	                                                                                                                                    @Override
	                                                                                                                                    public void run() {
	                                                                                                                                        p.sendTitle("§4PvP", "§8Developed b");
	                                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                           
	                                                                                                                                            @Override
	                                                                                                                                            public void run() {
	                                                                                                                                                p.sendTitle("§4PvP", "§8Developed by");
	                                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                   
	                                                                                                                                                    @Override
	                                                                                                                                                    public void run() {
	                                                                                                                                                        p.sendTitle("§4PvP", "§8Developed by M");
	                                                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                           
	                                                                                                                                                            @Override
	                                                                                                                                                            public void run() {
	                                                                                                                                                                p.sendTitle("§4PvP", "§8Developed by Me");
	                                                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                   
	                                                                                                                                                                    @Override
	                                                                                                                                                                    public void run() {
	                                                                                                                                                                        p.sendTitle("§4PvP", "§8Developed by Mer");
	                                                                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                           
	                                                                                                                                                                            @Override
	                                                                                                                                                                            public void run() {
	                                                                                                                                                                                p.sendTitle("§4PvP", "§8Developed by Mert");
	                                                                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                   
	                                                                                                                                                                                    @Override
	                                                                                                                                                                                    public void run() {
	                                                                                                                                                                                        p.sendTitle("§4PvP", "§8Developed by Merte");
	                                                                                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                           
	                                                                                                                                                                                            @Override
	                                                                                                                                                                                            public void run() {
	                                                                                                                                                                                                p.sendTitle("§4PvP", "§8Developed by Merter");
	                                                                                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                                   
	                                                                                                                                                                                                    @Override
	                                                                                                                                                                                                    public void run() {
	                                                                                                                                                                                                        p.sendTitle("§4PvP", "§8Developed by Mertero");
	                                                                                                                                                                                                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
	                                                                                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                                           
	                                                                                                                                                                                                            @Override
	                                                                                                                                                                                                            public void run() {
	                                                                                                                                                                                                                p.sendTitle("§cPvP", "§6Developed by Mertero");
	                                                                                                                                                                                                                p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                                                                Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                                                   
	                                                                                                                                                                                                                    @Override
	                                                                                                                                                                                                                    public void run() {
	                                                                                                                                                                                                                        p.sendTitle("§2PvP", "§bDeveloped by Mertero");
	                                                                                                                                                                                                                        p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
	                                                                                                                                                                                                                        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), new Runnable() {
	                                                                                                                                                                                                                           
	                                                                                                                                                                                                                            @Override
	                                                                                                                                                                                                                            public void run() {
	                                                                                                                                                                                                                                p.sendTitle("§bPvP", "§aDeveloped by Mertero");
	                                                                                                                                                                                                                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
	                                                                                                                                                                                                                            }
	                                                                                                                                                                                                                        }, 5*1);
	                                                                                                                                                                                                                    }
	                                                                                                                                                                                                                }, 5*1);
	                                                                                                                                                                                                            }
	                                                                                                                                                                                                        }, 5*1);
	                                                                                                                                                                                                    }
	                                                                                                                                                                                                }, 5*1);
	                                                                                                                                                                                            }
	                                                                                                                                                                                        }, 5*1);
	                                                                                                                                                                                    }
	                                                                                                                                                                                }, 5*1);
	                                                                                                                                                                            }
	                                                                                                                                                                        }, 5*1);
	                                                                                                                                                                    }
	                                                                                                                                                                }, 5*1);
	                                                                                                                                                            }
	                                                                                                                                                        }, 5*1);
	                                                                                                                                                    }
	                                                                                                                                                }, 5*1);
	                                                                                                                                            }
	                                                                                                                                        }, 5*1);
	                                                                                                                                    }
	                                                                                                                                }, 5*1);
	                                                                                                                            }
	                                                                                                                        }, 5*1);
	                                                                                                                    }
	                                                                                                                }, 5*1);
	                                                                                                            }
	                                                                                                        }, 5*1);
	                                                                                                    }
	                                                                                                }, 5*1);
	                                                                                            }
	                                                                                        }, 5*1);
	                                                                                    }
	                                                                                }, 5*1);
	                                                                            }
	                                                                        }, 5*1);
	                                                                    }
	                                                                }, 5*1);
	                                                            }
	                                                        }, 5*1);
	                                                    }
	                                                }, 5*1);
	                                               
	                                            }
	                                        }, 5*1);
	                                       
	                                    }
	                                }, 5*1);
	                            }
	                        }, 5*1);
	                       
	                    }
	                }, 5*1);
	            }
	        }
	        return false;
	    }
	   
	 

}
