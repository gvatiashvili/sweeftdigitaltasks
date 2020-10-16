package com.task.rssfeed;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict = false)
public class RssChannel
{
    @Element
    private String title;

    @ElementList(inline = true, required = false)
    public List<RssItem> item;

    @Override
    public String toString() {
        return "Channel [item=" + item + "]";
    }
}