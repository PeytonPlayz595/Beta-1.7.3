package net.minecraft.src;

public class RecipesWeapons {
	private String[][] recipePatterns = new String[][]{{"X", "X", "#"}};
	private Object[][] recipeItems = new Object[][]{{Block.planks, Block.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold}, {Item.swordWood, Item.swordStone, Item.swordSteel, Item.swordDiamond, Item.swordGold}};

	public void addRecipes(CraftingManager var1) {
		for(int var2 = 0; var2 < this.recipeItems[0].length; ++var2) {
			Object var3 = this.recipeItems[0][var2];

			for(int var4 = 0; var4 < this.recipeItems.length - 1; ++var4) {
				Item var5 = (Item)this.recipeItems[var4 + 1][var2];
				var1.addRecipe(new ItemStack(var5), new Object[]{this.recipePatterns[var4], Character.valueOf('#'), Item.stick, Character.valueOf('X'), var3});
			}
		}

		var1.addRecipe(new ItemStack(Item.bow, 1), new Object[]{" #X", "# X", " #X", Character.valueOf('X'), Item.silk, Character.valueOf('#'), Item.stick});
		var1.addRecipe(new ItemStack(Item.arrow, 4), new Object[]{"X", "#", "Y", Character.valueOf('Y'), Item.feather, Character.valueOf('X'), Item.flint, Character.valueOf('#'), Item.stick});
	}
}
