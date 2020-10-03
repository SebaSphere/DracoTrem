package dev.sebastianb.dracotrem.blocks.multiblock;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class EndAlterMultiblock {

    public static final ArrayList<Vec3i> dragonEggAlter = new ArrayList<Vec3i>(
            Arrays.asList(
                    //layer 1
                    new Vec3i(2,0,2),
                    new Vec3i(-2,0,2),
                    new Vec3i(2,0,-2),
                    new Vec3i(-2,0,-2),
                    new Vec3i(3,0,0),
                    new Vec3i(-3,0,0),
                    new Vec3i(0,0,-3),
                    new Vec3i(0,0,3),

                    //layer 2
                    new Vec3i(2,1,2),
                    new Vec3i(-2,1,2),
                    new Vec3i(2,1,-2),
                    new Vec3i(-2,1,-2),
                    new Vec3i(3,1,0),
                    new Vec3i(-3,1,0),
                    new Vec3i(0,1,-3),
                    new Vec3i(0,1,3)
            )
    );

    public static final ArrayList<Vec3d> dragonEggAlterEntity = new ArrayList<Vec3d>(
            Arrays.asList(

                    new Vec3d(2,2,2),
                    new Vec3d(-2,2,2),
                    new Vec3d(2,2,-2),
                    new Vec3d(-2,2,-2),
                    new Vec3d(3,2,0),
                    new Vec3d(-3,2,0),
                    new Vec3d(0,2,-3),
                    new Vec3d(0,2,3)
            )
    );






}
