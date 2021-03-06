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
                    new Vec3i(0,35,-40),
                    new Vec3i(-26,35,-26),
                    new Vec3i(-40,35,0),
                    new Vec3i(-26,35,26),
                    new Vec3i(0,35,40),
                    new Vec3i(26,35,26),
                    new Vec3i(40,35,0),
                    new Vec3i(26,35,-26),
                    new Vec3i(0,40,0) //resting pos
            )
    );

}
