package io.github.MomoNyako.Eat_Egg.eat_egg.neoforge;

import io.github.MomoNyako.Eat_Egg.eat_egg.EatEgg;
import io.github.MomoNyako.Eat_Egg.eat_egg.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(EatEgg.MOD_ID)
public class EatEggNeoForge {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(EatEgg.MOD_ID);

    public static final DeferredItem<?> BACON_EGGS = ITEMS.register("bacon_eggs", ModItems::baconEggs);
    public static final DeferredItem<?> COOKED_EGG = ITEMS.register("cooked_egg", ModItems::cookedEgg);
    public static final DeferredItem<?> FULL_ENG_BREAKFAST = ITEMS.register("full_eng_breakfast", ModItems::fullEngBreakfast);
    public static final DeferredItem<?> OMELETTE = ITEMS.register("omelette", ModItems::omelette);

    public EatEggNeoForge(IEventBus modBus) {
        ITEMS.register(modBus);
        modBus.addListener(this::onCommonSetup);
        modBus.addListener(this::addCreativeTabItems);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(BACON_EGGS.get(), 0.5f);
            ComposterBlock.COMPOSTABLES.put(COOKED_EGG.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(OMELETTE.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(FULL_ENG_BREAKFAST.get(), 0.8f);
        });
    }

    private void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(new ItemStack(BACON_EGGS.get()));
            event.accept(new ItemStack(COOKED_EGG.get()));
            event.accept(new ItemStack(FULL_ENG_BREAKFAST.get()));
            event.accept(new ItemStack(OMELETTE.get()));
        }
    }
}
