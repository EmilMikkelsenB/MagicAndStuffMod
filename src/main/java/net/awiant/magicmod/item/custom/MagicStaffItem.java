package net.awiant.magicmod.item.custom;

import net.awiant.magicmod.entity.ModEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.awiant.magicmod.entity.custom.MagicBolt;

public class MagicStaffItem extends Item {

    public MagicStaffItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        double reachDistance = 2000000.0d;

        // Perform ray tracing
        BlockHitResult hit = level.clip(new ClipContext(player.getEyePosition(), player.getEyePosition().add(player.getLookAngle().scale(reachDistance)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, player));
        if (!level.isClientSide) {
            // Calculate the look direction based on player's pitch and yaw
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = hit.getBlockPos();
                Vec3 playerPos = player.position();
                Vec3 lookDirection = player.getLookAngle();

                // Calculate the end position of the beam (you may adjust this)
                Vec3 endPos = playerPos.add(lookDirection.scale(reachDistance));

                // Spawn the MagicBolt entity
                MagicBolt magicBolt = new MagicBolt(ModEntities.MAGIC_BOLT.get(), level, hitPos, endPos);
                level.addFreshEntity(magicBolt);
            }
        } else { // Only on client-side (when the item is used)
            // You can perform client-side logic here
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}