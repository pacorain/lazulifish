package audio.paco.mc.lazulifish.command;

import audio.paco.mc.lazulifish.InsufficientCurrencyException;
import audio.paco.mc.lazulifish.PlayerNotFoundException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

import static org.bukkit.Bukkit.getPlayer;

public class ScareCommand implements CommandExecutor {

    private static final Random RANDOM = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!isValidArgumentSet(args))
            return false;
        processCommand(commandSender, command, s, args);
        return true;
    }

    private boolean isValidArgumentSet(String[] args) {
        return args.length > 0 && !args[0].isEmpty();
    }

    private void processCommand(CommandSender commandSender, Command command, String s, String[] args) {
        try {
            Player player = getPlayer(args[0]);
            checkCurrency(commandSender);
            scare(player);
            takeCurrency(commandSender);
        } catch (PlayerNotFoundException e) {
            TextComponent error = new TextComponent("Player ");
            error.setColor(ChatColor.RED);
            TextComponent playerName = new TextComponent(args[0]);
            playerName.setBold(true);
            error.addExtra(playerName);
            error.addExtra(" not found on this server.");
            sendTextComponent(commandSender, error);
        } catch (InsufficientCurrencyException e) {
            TextComponent error = new TextComponent("There are no emralds in your inventory!");
            error.setColor(ChatColor.RED);
            sendTextComponent(commandSender, error);
        }
    }

    private void checkCurrency(CommandSender sender) throws InsufficientCurrencyException {
        if (!(sender instanceof Player))
            return;
        Player player = (Player) sender;
        if (!player.getInventory().contains(Material.EMERALD))
            throw new InsufficientCurrencyException();
    }

    private void scare(Player player) throws PlayerNotFoundException {
        if (player == null || ! player.isOnline())
            throw new PlayerNotFoundException();
        player.playSound(player.getLocation().add(randomVector()), Sound.ENTITY_CREEPER_PRIMED, 100, 0);
    }

    private void takeCurrency(CommandSender sender) {
        if (!(sender instanceof Player))
            return;
        Player player = (Player) sender;
        for (ItemStack stack : player.getInventory())
            if (stack != null && stack.getType() == Material.EMERALD) {
                stack.setAmount(stack.getAmount() - 1);
                return;
            }
    }

    private Vector randomVector() {
        float x = RANDOM.nextFloat() * 2 - 1;
        float y = RANDOM.nextFloat() * 2 - 1;
        float z = RANDOM.nextFloat() * 2 - 1;
        return new Vector(x, y, z);
    }

    private void sendTextComponent(CommandSender reciever, TextComponent message) {
        if (reciever instanceof Player) {
            Player player = (Player) reciever;
            player.spigot().sendMessage(message);
        } else {
            reciever.sendMessage(message.toPlainText());
        }
    }


}
