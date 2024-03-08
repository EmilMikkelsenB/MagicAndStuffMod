package net.awiant.tutorialmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;


public class MagicStaffItem extends Item {
    public MagicStaffItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level plevel, Player pPlayer, InteractionHand hand) {
        ItemStack itemstack = pPlayer.getItemInHand(hand);

        if (!plevel.isClientSide) {
            // Calculate the look direction based on player's pitch and yaw
            double reachDistance = 2000000.0d;
            // Perform ray tracing
            BlockHitResult hit = plevel.clip(new ClipContext(pPlayer.getEyePosition(), pPlayer.getEyePosition().add(pPlayer.getLookAngle().scale(reachDistance)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, pPlayer));

            if (hit.getType() == HitResult.Type.BLOCK) {
                plevel.explode(null, hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ(), 5, Level.ExplosionInteraction.BLOCK);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, plevel.isClientSide());
    }
}
