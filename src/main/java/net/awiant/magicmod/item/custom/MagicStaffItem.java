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
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.Color;

public class MagicStaffItem extends Item {

    public MagicStaffItem(Properties properties) {
        super(properties);
    }

    public static void particleBlast(Level level, BlockPos pos) {
        Color startingColor = new Color(100, 0, 100);
        Color endingColor = new Color(100, 0, 100);

        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(100f, 0).build())
                .setTransparencyData(GenericParticleData.create(100f, 100f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(40)
                .addMotion(0, 0.01f, 0)
                .enableNoClip()
                .spawn(level, pos.getX(), pos.getY(), pos.getZ());



    }
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        double reachDistance = 2000000.0d;

        // Perform ray tracing
        BlockHitResult hit = level.clip(new ClipContext(player.getEyePosition(), player.getEyePosition().add(player.getLookAngle().scale(reachDistance)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, player));
        if (!level.isClientSide) {
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = hit.getBlockPos();
                Vec3 playerPos = player.position();
                Vec3 lookDirection = player.getLookAngle();
                level.explode(null, hitPos.getX(), hitPos.getY(), hitPos.getZ(), 4.0F, true, Level.ExplosionInteraction.TNT);level.explode(null, hitPos.getX(), hitPos.getY(), hitPos.getZ(), 10.0F, true, Level.ExplosionInteraction.TNT);

                // Calculate the end position of the beam (you may adjust this)
                Vec3 endPos = playerPos.add(lookDirection.scale(reachDistance));
                // Spawn the MagicBolt entity

            }
        } else { // Only on client-side (when the item is used)
            particleBlast(player.level(),hit.getBlockPos());
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
