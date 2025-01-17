package lv.id.bonne.vaultjewelsorting.storagenetwork;

import lv.id.bonne.vaultjewelsorting.config.MixinConfigPlugin;
import net.minecraftforge.fml.loading.LoadingModList;


/**
 * This Mixin configuration checks if Simple Storage Network mod is loaded.
 */
public class StorageNetworkIntegrationModConfiguration extends MixinConfigPlugin
{
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        return LoadingModList.get().getModFileById("storagenetwork") != null;
    }
}
