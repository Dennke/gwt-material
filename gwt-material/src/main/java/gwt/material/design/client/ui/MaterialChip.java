/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasImage;
import gwt.material.design.client.base.HasLetter;
import gwt.material.design.client.base.mixin.LetterMixin;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Chips can be used to represent small blocks of information.
 * They are most commonly used either for contacts or for tags.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code// Simple Chips
 * <m:MaterialChip text="Default" iconType="CLOSE"/>
 *
 * // Static Chip
 * <m:MaterialChip text="Apple" />
 *
 * // Contact Chips
 * <m:MaterialChip url="http://b.vimeocdn.com/ps/339/488/3394886_300.jpg" text="Yunalis Mat Zara'ai" iconType="CLOSE"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#chips">Material Chips</a>
 * @see <a href="https://material.io/guidelines/components/chips.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialChip extends AbstractValueWidget<String> implements HasImage, HasIcon, HasLetter, HasValue<String>, HasCloseHandlers {

    private MaterialIcon icon = new MaterialIcon(IconType.CLOSE);
    private Span chipLabel = new Span();

    private ImageResource resource;
    private Image image = new Image();

    private LetterMixin<MaterialChip> letterMixin;

    /**
     * Creates an empty chip.
     */
    public MaterialChip() {
        super(Document.get().createDivElement(), CssName.CHIP);
        add(chipLabel);
    }

    public MaterialChip(String text) {
        this();
        setText(text);
    }

    public MaterialChip(String text, IconType icon) {
        this(text);
        setIconType(icon);
    }

    public MaterialChip(String text, String imageUrl) {
        this(text);
        setUrl(imageUrl);
    }

    public MaterialChip(String text, Color bgColor, Color textColor) {
        this(text);
        setBackgroundColor(bgColor);
        setTextColor(textColor);
    }

    @Deprecated
    public MaterialChip(String text, String bgColor, String textColor) {
        this(text, Color.fromStyleName(bgColor), Color.fromStyleName(textColor));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        registerHandler(icon.addClickHandler(clickEvent -> close()));
    }

    public void setText(String text) {
        setValue(text, true);
    }

    public String getText() {
        return getValue();
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        chipLabel.setText(value);
        super.setValue(value, fireEvents);
    }

    @Override
    public String getValue() {
        return chipLabel.getElement().getInnerText();
    }

    @Override
    public void setUrl(String url) {
        image.setUrl(url);
        add(image);
    }

    @Override
    public String getUrl() {
        return image.getUrl();
    }

    @Override
    public void setResource(ImageResource resource) {
        this.resource = resource;
        image.setResource(resource);
        add(image);
    }

    @Override
    public ImageResource getResource() {
        return resource;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        add(icon);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    public void setLetter(String letter) {
        getLetterMixin().setLetter(letter);
    }

    @Override
    public String getLetter() {
        return getLetterMixin().getLetter();
    }

    @Override
    public void setLetterColor(Color letterColor) {
        getLetterMixin().setLetterColor(letterColor);
    }

    @Override
    public void setLetterBackgroundColor(Color letterBackgroundColor) {
        getLetterMixin().setLetterBackgroundColor(letterBackgroundColor);
    }

    public LetterMixin<MaterialChip> getLetterMixin() {
        if (letterMixin == null) {
            letterMixin = new LetterMixin<>(this);
        }
        return letterMixin;
    }

    public Span getChipLabel() {
        return chipLabel;
    }

    public void close() {
        if (isAttached()) {
            removeFromParent();
            CloseEvent.fire(this, this);
        }
    }

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler closeHandler) {
        return addHandler(closeHandler, CloseEvent.getType());
    }
}
