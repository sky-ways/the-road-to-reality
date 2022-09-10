package com.github.cao.awa.trtr.power.thermoelectric.fire.burner;

import com.github.cao.awa.trtr.properties.*;
import com.github.cao.awa.trtr.type.*;
import com.mojang.blaze3d.systems.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class BurnerScreen extends HandledScreen<BurnerScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("trtr:textures/gui/container/burner.png");
    //    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/dispenser.png");

    public static void register() {
        HandledScreens.register(TrtrScreenHandlerType.BURNER, BurnerScreen::new);
    }

    public BurnerScreen(BurnerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);

        BlockEntityProperties<BurnerBlockEntity> properties = this.handler.getProperties();

        int k;
        int burningTime = properties.getIntOrDefault("burningTime", 0);
        if (burningTime > 0) {
            int fuelTime = properties.getIntOrDefault("fuelTime", 200);
            k = burningTime * 13 / fuelTime;
            this.drawTexture(matrices, i + 80, j + 19 + 12 - k, 176, 12 - k, 14, k + 1);
        }
    }

    public void handledScreenTick() {
        super.handledScreenTick();
    }
}
