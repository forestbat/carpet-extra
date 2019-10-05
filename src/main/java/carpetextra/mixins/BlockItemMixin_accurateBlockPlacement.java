package carpetextra.mixins;

import carpetextra.CarpetExtraSettings;
import carpetextra.utils.BlockPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockItem.class)
public class BlockItemMixin_accurateBlockPlacement
{
    @Shadow
    private Block block //It's deprecated,but it's convenience
    
    @SoftOverride
    private BlockState getAlternatePlacement(ItemPlacementContext itemPlacementContext_1)
    {
        if (CarpetExtraSettings.accurateBlockPlacement)
        {
            BlockState tryAlternative = BlockPlacer.alternativeBlockPlacement(block, itemPlacementContext_1);
            if (tryAlternative != null)
                return tryAlternative;
        }
        return block.getPlacementState(itemPlacementContext_1);
    }

}
