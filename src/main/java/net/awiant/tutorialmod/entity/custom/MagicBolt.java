package net.awiant.tutorialmod.entity.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class MagicBolt extends Entity {

    // Define parameters for MagicBolt entity
    private BlockPos pos1;
    private Vec3 pos2;

    public MagicBolt(EntityType<?> pEntityType, Level pLevel, BlockPos pos1, Vec3 pos2) {
        super(pEntityType, pLevel);
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
    public void renderBeam(BlockPos pos1, Vec3 pos2, float power) {
        VertexConsumer consumer = null;
        VFXBuilders.createWorld()
                .renderBeam(consumer, poseStack, pos1, pos2);
    }

    @Override
    protected void defineSynchedData() {
        // Define synchronized data if needed
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        // Read additional save data if needed
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        // Add additional save data if needed
    }

    // Add any additional methods or functionality for MagicBolt entity
}
