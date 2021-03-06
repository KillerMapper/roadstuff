package tv.mapper.roadstuff.data.gen;

import java.util.Arrays;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import tv.mapper.mapperbase.block.BaseBlocks;
import tv.mapper.mapperbase.block.PaintableBlock;
import tv.mapper.roadstuff.RoadStuff;
import tv.mapper.roadstuff.block.RSBlockRegistry;
import tv.mapper.roadstuff.data.RSTags;

public class RSBlockTags extends BlockTagsProvider
{
    public RSBlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper)
    {
        super(generatorIn, RoadStuff.MODID, existingFileHelper);
    }

    public void registerTags()
    {
        this.getOrCreateBuilder(RSTags.Blocks.ASPHALT_ROAD).add(BaseBlocks.ASPHALT.get(), RSBlockRegistry.ASPHALT_SLOPE.get());
        this.getOrCreateBuilder(RSTags.Blocks.CONCRETE_ROAD).add(BaseBlocks.CONCRETE.get(), RSBlockRegistry.CONCRETE_SLOPE.get());

        for(RegistryObject<PaintableBlock> block : RSBlockRegistry.MOD_PAINTABLEBLOCKS)
        {
            String[] raw = block.get().getTranslationKey().split("_");

            if(raw[0].contains("asphalt"))
                this.getOrCreateBuilder(RSTags.Blocks.ASPHALT_ROAD).add(block.get());
            else
                this.getOrCreateBuilder(RSTags.Blocks.CONCRETE_ROAD).add(block.get());
        }

        for(int i = 0; i < Arrays.stream(DyeColor.values()).count(); i++)
        {
            this.getOrCreateBuilder(RSTags.Blocks.REFLECTORS).add(RSBlockRegistry.REFLECTOR_BLOCKS.get(DyeColor.byId(i)).get());
        }
    }
}