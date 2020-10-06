package dev.sebastianb.dracotrem.blocks.multiblock.dragonbossalter;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.Arrays;


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
    public static final ArrayList<Vec3i> dragonEggAlterEntity = new ArrayList<Vec3i>(
            Arrays.asList(
                    new Vec3i(2,2,2),
                    new Vec3i(-2,2,2),
                    new Vec3i(2,2,-2),
                    new Vec3i(-2,2,-2),
                    new Vec3i(3,2,0),
                    new Vec3i(-3,2,0),
                    new Vec3i(0,2,-3),
                    new Vec3i(0,2,3)
            )
    );
    public static final ArrayList<Vec3i> dragonAlterIslandLocation = new ArrayList<Vec3i>(
            Arrays.asList(
                    new Vec3i(1,2,3),
                    new Vec3i(5,7,-6),
                    new Vec3i(-12,2,3),
                    new Vec3i(-13,10,3),
                    new Vec3i(-12,2,-5),
                    new Vec3i(12,8,-15),
                    new Vec3i(6,8,-10),
                    new Vec3i(10,10,-15)
            )
    );

}
