package net.yak.electronicsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class pin extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final IntegerProperty PIN = IntegerProperty.create("pin",1,64);
    public pin(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, Boolean.valueOf(false)));
    }



    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(ACTIVE, Boolean.valueOf(pContext.getLevel().hasNeighborSignal(pContext.getClickedPos())));
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(ACTIVE);
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(ACTIVE), 2);
                }
            }

        }
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pState.getValue(ACTIVE) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(ACTIVE), 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVE);
    }
}
