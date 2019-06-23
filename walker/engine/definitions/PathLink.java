package com.dax.walker.engine.definitions;

import com.dax.walker.engine.EntityHandler;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.movement.transportation.CharterShip;
import org.rspeer.ui.Log;

import java.util.logging.Level;
import java.util.regex.Pattern;

public enum PathLink {

    PORT_SARIM_KARAMJA(
            new Position(3029, 3217, 0), new Position(2953, 3146, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)pay.fare"), start, end, walkCondition)
    ),

    KARAMJA_PORT_SARIM(
            new Position(2953, 3146, 0), new Position(3029, 3217, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)pay.fare"), start, end, walkCondition)
    ),

    PORT_SARIM_CATHERBY(
            new Position(3041, 3193, 0), new Position(2796, 3414, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.CATHERBY, walkCondition)
    ),

    PORT_SARIM_BRIMHAVEN(
            new Position(3041, 3193, 0), new Position(2760, 3237, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.BRIMHAVEN, walkCondition)
    ),

    CATHERBY_PORT_SARIM(
            new Position(2796, 3414, 0), new Position(3041, 3193, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.PORT_SARIM, walkCondition)
    ),

    CATHERBY_PORT_BRIMHAVEN(
            new Position(2796, 3414, 0), new Position(2760, 3237, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.BRIMHAVEN, walkCondition)
    ),

    CATHERBY_MUSA_POINT(
            new Position(2796, 3414, 0), new Position(2953, 3146, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.MUSA_POINT, walkCondition)
    ),

    BRIMHAVEN_ARDOUGHNE(
            new Position(2772, 3225, 0), new Position(2681, 3275, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)pay.fare"), start, end, walkCondition)
    ),

    ARDOUGHNE_BRIMHAVEN(
            new Position(2681, 3275, 0), new Position(2772, 3225, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)pay.fare"), start, end, walkCondition)
    ),

    KHAZARD_CATHERBY(
            new Position(2673, 3148, 0), new Position(2796, 3414, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.CATHERBY, walkCondition)
    ),

    KHAZARD_PORT_SARIM(
            new Position(2673, 3148, 0), new Position(3041, 3193, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.PORT_SARIM, walkCondition)
    ),

    PORT_SARIM_KHAZARD(
            new Position(3041, 3193, 0), new Position(2673, 3148, 0),
            (start, end, walkCondition) -> EntityHandler.handleCharter(CharterShip.Destination.PORT_KHAZARD, walkCondition)
    ),

    PORT_SARIM_PEST_CONTROL(
            new Position(3041, 3202, 0), new Position(2659, 2676, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)travel"), start, end, walkCondition)
    ),

    PEST_CONTROL_PORT_SARIM(
            new Position(2659, 2676, 0), new Position(3041, 3202, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)travel"), start, end, walkCondition)
    ),

    PORT_SARIM_PISCARILIUS(
            new Position(3054, 3245, 0), new Position(1824, 3691, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)port pis.+"), start, end, walkCondition)
    ),

    PORT_SARIM_LANDS_END(
            new Position(3054, 3245, 0), new Position(1504, 3399, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)land.s end"), start, end, walkCondition)
    ),

    LANDS_END_PISCARILIUS(
            new Position(1504, 3399, 0), new Position(1824, 3691, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)port pis.+"), start, end, walkCondition)
    ),

    LANDS_END_PORT_SARIM(
            new Position(1504, 3399, 0), new Position(3054, 3245, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)port sarim"), start, end, walkCondition)
    ),

    PISCARILIUS_LANDS_END(
            new Position(1824, 3691, 0), new Position(1504, 3399, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)land.s end"), start, end, walkCondition)
    ),

    PISCARILIUS_PORT_SARIM(
            new Position(1824, 3691, 0), new Position(3054, 3245, 0),
            (start, end, walkCondition) -> EntityHandler.handleWithAction(Pattern.compile("(?i)port sarim"), start, end, walkCondition)
    ),



    ;

    private Position start;
    private Position end;
    private PathLinkHandler pathLinkHandler;

    PathLink(Position start, Position end, PathLinkHandler pathLinkHandler) {
        this.start = start;
        this.end = end;
        this.pathLinkHandler = pathLinkHandler;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public PathHandleState handle(WalkCondition walkCondition) {
        Log.log(Level.FINE, "DaxWalker", "Triggering " + this);
        return this.pathLinkHandler.handle(start, end, walkCondition);
    }

}
