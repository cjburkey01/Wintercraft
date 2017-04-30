package com.cjburkey.mod.wonderland.gui;

import com.cjburkey.mod.wonderland.ModInfo;
import com.cjburkey.mod.wonderland.container.ContainerGift;
import com.cjburkey.mod.wonderland.tile.TileEntityGift;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public final class GuiGift extends GuiContainer {
	
	private IInventory playerInventory;
	private TileEntityGift tileEntityGift;
	
	public GuiGift(IInventory player, TileEntityGift tileEntity) {
		super(new ContainerGift(player, tileEntity));
		
		playerInventory = player;
		tileEntityGift = tileEntity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(new ResourceLocation(ModInfo.ID, "textures/gui/container/gui_gift.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = tileEntityGift.getDisplayName().getUnformattedText();
		fontRendererObj.drawString(name, 88 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, 72, 0x404040);
	}
	
}