package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(Block.chest), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", Character.valueOf('#'), Block.cobblestone});
		var1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", Character.valueOf('#'), Block.planks});
		var1.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", Character.valueOf('#'), Block.sand});
	}
}
