//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaultjewelsorting.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.init.ModGearAttributes;
import net.minecraftforge.event.RegistryEvent;


/**
 * The class that helps to deal with Vault Hunters Gear Attributes.
 */
public class AttributeHelper
{
    /**
     * This method populates all VaultGearAttributes into custom lists.
     */
    public static void registerAttributes(RegistryEvent.Register<VaultGearAttribute<?>> event)
    {
        MOD_GEAR_ATTRIBUTE.clear();
        MOD_GEAR_ATTRIBUTE.addAll(event.getRegistry().getValues());
    }


    public static void registerAttributes()
    {
        MOD_GEAR_ATTRIBUTE.clear();
        FLOAT_ATTRIBUTE.clear();
        INTEGER_ATTRIBUTE.clear();
        DOUBLE_ATTRIBUTE.clear();

        for (Field field : ModGearAttributes.class.getDeclaredFields())
        {
            try
            {
                Object fieldObject = field.get(ModGearAttributes.class);

                if (fieldObject instanceof VaultGearAttribute<?> attribute)
                {
                    MOD_GEAR_ATTRIBUTE.add(attribute);

                    if (field.getGenericType().getTypeName().endsWith("<java.lang.Integer>"))
                    {
                        INTEGER_ATTRIBUTE.add(attribute);
                    }
                    else if (field.getGenericType().getTypeName().endsWith("<java.lang.Float>"))
                    {
                        FLOAT_ATTRIBUTE.add(attribute);
                    }
                    else if (field.getGenericType().getTypeName().endsWith("<java.lang.Double>"))
                    {
                        DOUBLE_ATTRIBUTE.add(attribute);
                    }
                }
            }
            catch (IllegalAccessException ignored)
            {
            }
        }
    }


    /**
     * Is float attribute boolean.
     *
     * @param attribute the attribute
     * @return the boolean
     */
    public static boolean isFloatAttribute(VaultGearAttribute<?> attribute)
    {
        return FLOAT_ATTRIBUTE.contains(attribute);
    }


    /**
     * Is integer attribute boolean.
     *
     * @param attribute the attribute
     * @return the boolean
     */
    public static boolean isIntegerAttribute(VaultGearAttribute<?> attribute)
    {
        return INTEGER_ATTRIBUTE.contains(attribute);
    }


    /**
     * Is double attribute boolean.
     *
     * @param attribute the attribute
     * @return the boolean
     */
    public static boolean isDoubleAttribute(VaultGearAttribute<?> attribute)
    {
        return DOUBLE_ATTRIBUTE.contains(attribute);
    }


    /**
     * Gets attribute index.
     *
     * @param attribute the attribute
     * @return the attribute index
     */
    public static int getAttributeIndex(VaultGearAttribute<?> attribute)
    {
        return MOD_GEAR_ATTRIBUTE.indexOf(attribute);
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


    /**
     * List that holds all VaultGearAttributes.
     */
    private static final List<VaultGearAttribute<?>> MOD_GEAR_ATTRIBUTE = new ArrayList<>();

    /**
     * The set that holds all VaultGearAttributes that are float.
     */
    private static final Set<VaultGearAttribute<?>> FLOAT_ATTRIBUTE = new HashSet<>();

    /**
     * The set that holds all VaultGearAttributes that are integer.
     */
    private static final Set<VaultGearAttribute<?>> INTEGER_ATTRIBUTE = new HashSet<>();

    /**
     * The set that holds all VaultGearAttributes that are double.
     */
    private static final Set<VaultGearAttribute<?>> DOUBLE_ATTRIBUTE = new HashSet<>();
}
