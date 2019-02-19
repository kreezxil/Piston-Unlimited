package com.multipiston.coremod;

import com.multipiston.coremod.blocks.ModBlocks;
import com.multipiston.coremod.tileentities.TileEntityMultiPiston;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import static com.multipiston.coremod.Constants.INVENTORY;
import static com.multipiston.coremod.Constants.MULTIBLOCK_PREV_NAME;

@Mod.EventBusSubscriber
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies="required-after:blockout",
  /*dependencies = Constants.FORGE_VERSION,*/ acceptedMinecraftVersions = Constants.MC_VERSION)
public class MultiPiston
{
    private static final Logger logger = LogManager.getLogger(Constants.MOD_ID);

    /**
     * Forge created instance of the Mod.
     */
    @Mod.Instance(Constants.MOD_ID)
    public static MultiPiston instance;

    /**
     * Getter for the multipiston Logger.
     *
     * @return the logger.
     */
    public static Logger getLogger()
    {
        return logger;
    }

    /**
     * Event handler for forge pre init event.
     *
     * @param event the forge pre init event.
     */
    @Mod.EventHandler
    public void preInit(@NotNull final FMLPreInitializationEvent event)
    {
        @NotNull final Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }


    /**
     * Called when registering blocks,
     * we have to register all our modblocks here.
     *
     * @param event the registery event for blocks.
     */
    @SubscribeEvent
    public static void registerBlocks(@NotNull final RegistryEvent.Register<Block> event)
    {
        ModBlocks.init(event.getRegistry());
    }

    /**
     * Called when registering items,
     * we have to register all our mod items here.
     *
     * @param event the registery event for items.
     */
    @SubscribeEvent
    public static void registerItems(@NotNull final RegistryEvent.Register<Item> event)
    {
        ModBlocks.registerItemBlock(event.getRegistry());
    }

    /**
     * Event handler for forge init event.
     *
     * @param event the forge init event.
     */
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event)
    {
        GameRegistry.registerTileEntity(TileEntityMultiPiston.class, Constants.MOD_ID + ".MultiPiston");
    }

    /**
     * Event handler for forge ModelRegistryEvent event.
     *
     * @param event the forge pre ModelRegistryEvent event.
     */
    @SubscribeEvent
    public static void registerModels(@NotNull final ModelRegistryEvent event)
    {
        createCustomModel(ModBlocks.andesite);
        createCustomModel(ModBlocks.diorite);
        createCustomModel(ModBlocks.granite);
        createCustomModel(ModBlocks.stone);
        createCustomModel(ModBlocks.oak);
        createCustomModel(ModBlocks.birch);
        createCustomModel(ModBlocks.acacia);
        createCustomModel(ModBlocks.spruce);
        createCustomModel(ModBlocks.darkOak);
        createCustomModel(ModBlocks.jungleWood);
    }

    /**
     * Creates a custom model ResourceLocation for a block with metadata 0
     */
    private static void createCustomModel(final Block block)
    {
        final Item item = Item.getItemFromBlock(block);
        if (item != Items.AIR)
        {
            ModelLoader.setCustomModelResourceLocation(item, 0,
              new ModelResourceLocation(block.getRegistryName(), INVENTORY));
        }
    }

    /**
     * Called when the config is changed, used to synch between file and game.
     *
     * @param event the on config changed event.
     */
  /*  @SubscribeEvent
    public void missingMapping(@NotNull final RegistryEvent.MissingMappings<Block> event)
    {
        final IForgeRegistry registry = event.getRegistry();
        event.getAllMappings().forEach((mapping) -> {
            final String mappingString = mapping.key.toString();
            if(mappingString.contains(Constants.MINECOLONIES_MOD_ID) && mappingString.contains(MULTIBLOCK_PREV_NAME))
            {
                mapping.remap(ModBlocks.andesite);
            }
        });
    }
*/
}