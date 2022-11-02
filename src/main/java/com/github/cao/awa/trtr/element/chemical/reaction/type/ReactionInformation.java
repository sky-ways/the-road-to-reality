package com.github.cao.awa.trtr.element.chemical.reaction.type;

import com.github.cao.awa.trtr.element.chemical.reaction.amplifier.*;

public record ReactionInformation(double temperature, double pressure, double voltage, ReactionAmplifier amplifier) {
}
