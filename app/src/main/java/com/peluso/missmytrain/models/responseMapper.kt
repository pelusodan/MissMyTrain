package com.peluso.missmytrain.models

import java.util.ArrayList
import java.util.function.BiFunction

class responseMapper : BiFunction<MapQuestResponse,MBTAResponse,List<RecyclerViewCell>> {
    override fun apply(t: MapQuestResponse, u: MBTAResponse): List<RecyclerViewCell> {
        var out = ArrayList<RecyclerViewCell>()
        u.data.forEach {
            out.add(RecyclerViewCell(t.route.formattedTime,it.attributes.arrival_time,it.relationships.route.data.id,it.attributes.direction_id))
        }
        return out
    }
}