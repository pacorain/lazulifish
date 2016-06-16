package audio.paco.mc.lazulifish.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        TextComponent chatMessage = new TextComponent("Visit ");
        chatMessage.setColor(ChatColor.BLUE);
        TextComponent link = new TextComponent("https://discord.gg/014lru1BM0FQg3I6G");
        link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/014lru1BM0FQg3I6G"));
        link.setUnderlined(true);
        chatMessage.addExtra(link);
        chatMessage.addExtra(" to join the server chat.");
        try {
            Player player = (Player) commandSender;
            player.spigot().sendMessage(chatMessage);
        } catch (ClassCastException e) {
            commandSender.sendMessage(chatMessage.toPlainText());
        }
        return true;
    }
}
