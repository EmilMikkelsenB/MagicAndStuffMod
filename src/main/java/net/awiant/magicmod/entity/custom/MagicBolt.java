package net.awiant.magicmod.entity.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class MagicBolt extends Entity {

    private static final ResourceLocation LIGHT_TRAIL = new ResourceLocation("textures/vfx/concentrated_trail.png");
    private static final RenderType TRAIL_TYPE = LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.apply(LIGHT_TRAIL);
    private BlockPos pos1;
    private Vec3 pos2;

    public MagicBolt(EntityType<?> pEntityType, Level pLevel, BlockPos pos1, Vec3 pos2) {
        super(pEntityType, pLevel);
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
    public static void renderBeam(BlockPos pos1, Vec3 pos2, float width) {
        VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(TRAIL_TYPE);
        Matrix4f poseStack = new Matrix4f();
        VFXBuilders.createWorld()
                .renderBeam(consumer, poseStack, pos1.getCenter(), pos2, width);
    }

    @Override
    protected void defineSynchedData() {
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