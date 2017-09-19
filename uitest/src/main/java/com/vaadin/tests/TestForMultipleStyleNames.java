/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.TwinColSelect;

/**
 * TODO: Note you need to add Theme under WebContent/VAADIN/Themes/mytheme in
 * order to see actual visible results on the browser. Currently changes are
 * visible only by inspecting DOM.
 *
 * @author Vaadin Ltd.
 */
public class TestForMultipleStyleNames extends CustomComponent
        implements ValueChangeListener {

    private final VerticalLayout main = new VerticalLayout();

    private Label l;

    private final TwinColSelect s = new TwinColSelect();

    private ArrayList<String> styleNames2;

    public TestForMultipleStyleNames() {
        setCompositionRoot(main);
        createNewView();
    }

    public void createNewView() {
        main.removeAllComponents();
        main.addComponent(
                new Label("TK5 supports multiple stylenames for components."));
        main.addComponent(new Label("Note you need to add Theme under"
                + " WebContent/VAADIN/Themes/mytheme"
                + " in order to see actual visible results"
                + " on the browser. Currently changes are"
                + " visible only by inspecting DOM."));

        styleNames2 = new ArrayList<>();

        styleNames2.add("red");
        styleNames2.add("bold");
        styleNames2.add("italic");

        s.setContainerDataSource(new IndexedContainer(styleNames2));
        s.addListener(this);
        s.setImmediate(true);
        main.addComponent(s);

        l = new Label("Test labele");
        main.addComponent(l);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void valueChange(ValueChangeEvent event) {

        final String currentStyle = l.getStyleName();
        final String[] tmp = currentStyle.split(" ");
        final ArrayList<String> curStyles = new ArrayList<>();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != "") {
                curStyles.add(tmp[i]);
            }
        }

        final Collection<String> styles = (Collection<String>) s.getValue();

        for (final String styleName : styles) {
            if (curStyles.contains(styleName)) {
                // already added
                curStyles.remove(styleName);
            } else {
                l.addStyleName(styleName);
            }
        }
        for (final String object : curStyles) {
            l.removeStyleName(object);
        }
    }

}
