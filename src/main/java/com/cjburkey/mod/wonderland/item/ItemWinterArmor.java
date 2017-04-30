package com.cjburkey.mod.wonderland.item;

import com.cjburkey.mod.wonderland.Log;
import com.cjburkey.mod.wonderland.material.ModMaterials;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class ItemWinterArmor extends ItemArmor {
	
	private static Potion speedPotion;
	
	public ItemWinterArmor(int renderIndexIn, EntityEquipmentSlot type) {
		super(ModMaterials.materialArmorWinter, renderIndexIn, type);
	}
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(speedPotion == null) speedPotion = Potion.getPotionById(1);
		if(!world.isRemote) {
			if(stack.getItemDamage() >= stack.getMaxDamage()) stack.setCount(0);
			if(player.inventory.armorItemInSlot(0).getItem().equals(ModItems.itemWinterBoots) && aboveIce(player)) addEffect(player, speedPotion);
			else if(!aboveIce(player)) removeEffect(player, speedPotion);
		}
	}
	
	private void addEffect(EntityPlayer player, Potion potion) {
		if(player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1) {
			player.addPotionEffect(new PotionEffect(potion, 159, 3));
		}
	}
	
	private void removeEffect(EntityPlayer player, Potion potion) {
		if(player.getActivePotionEffect(potion) != null && player.getActivePotionEffect(potion).getDuration() > 20) {
			player.removeActivePotionEffect(potion);
			player.addPotionEffect(new PotionEffect(potion, 10, 3));
		}
	}
	
	private boolean aboveIce(EntityPlayer player) {
		BlockPos plyPos = player.getPosition();
		BlockPos below = new BlockPos(plyPos.getX(), plyPos.getY() - 1, plyPos.getZ());
		float slippery = player.world.getBlockState(below).getBlock().slipperiness;
		return slippery > 0.7f;
	}
	
}