package kr.syeyoung.dungeonsguide.roomprocessor;

import kr.syeyoung.dungeonsguide.dungeon.roomfinder.DungeonRoom;
import kr.syeyoung.dungeonsguide.events.PlayerInteractEntityEvent;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public interface RoomProcessor {
    void tick();
    void drawScreen(float partialTicks);
    void drawWorld(float partialTicks);
    void chatReceived(IChatComponent chat);
    void actionbarReceived(IChatComponent chat);

    boolean readGlobalChat();

    void onPostGuiRender(GuiScreenEvent.DrawScreenEvent.Post event);
    void onEntitySpawn(LivingEvent.LivingUpdateEvent updateEvent);

    void onKeyPress(InputEvent.KeyInputEvent keyInputEvent);

    void onInteract(PlayerInteractEntityEvent event);
}