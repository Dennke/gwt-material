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

import gwt.material.design.client.constants.DatePickerContainer;
import gwt.material.design.client.constants.DatePickerLanguage;
import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.js.JsMaterialElement;
import gwt.material.design.client.ui.base.AbstractValueWidgetTest;

import java.util.Date;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Test case for Datepicker.
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialDatePickerTest extends AbstractValueWidgetTest<MaterialDatePicker> {

    @Override
    protected MaterialDatePicker createWidget() {
        return new MaterialDatePicker();
    }

    public void testErrorSuccess() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        checkErrorSuccess(datePicker, datePicker.getDateInput());
    }

    // TODO
    public void testProperties() {

    }

    // TODO
    public void testStructure() {

    }

    public void testPlaceholder() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        datePicker.setPlaceholder("Placeholder");
        assertEquals("Placeholder", datePicker.getPlaceholder());
        datePicker.setPlaceholder("");
        assertEquals("", datePicker.getPlaceholder());
    }

    public void testContainer() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        // Check the default container - SELF
        assertEquals(DatePickerContainer.SELF, datePicker.getContainer());

        datePicker.setContainer(DatePickerContainer.BODY);
        assertEquals(DatePickerContainer.BODY, datePicker.getContainer());

        datePicker.setContainer(DatePickerContainer.SELF);
        assertEquals(DatePickerContainer.SELF, datePicker.getContainer());
    }

    public void testAutoClose() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        // Test multiple set to autoclose (leaks checking)
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, true);
        checkAutoClose(datePicker, false);
        checkAutoClose(datePicker, false);
    }

    // TODO Test Clear
    public void testClear() {}

    protected void checkAutoClose(MaterialDatePicker datePicker, boolean autoClose) {
        datePicker.setAutoClose(autoClose);
        if (autoClose) {
            assertTrue(datePicker.isAutoClose());
        } else {
            assertFalse(datePicker.isAutoClose());
        }
    }

    public void testOpenCloseControl() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        datePicker.setEnabled(true);
        assertTrue(datePicker.isEnabled());
        checkOpenHandler(datePicker);
        checkCloseHandler(datePicker);
    }

    public void testFormat() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        final String FORMAT = "mm/dd/yyyy";
        datePicker.setFormat(FORMAT);
        assertEquals(FORMAT, datePicker.getFormat());
    }

    @Override
    public void testEnabled() {
        super.testEnabled();

        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        datePicker.setEnabled(true);
        // Check Initial Tab index value for Root Picker Element - tabindex = 0
        assertEquals("0", $(datePicker.getPickerRootElement()).attr("tabindex"));
        // Expected when datepicker is disabled - tabindex = -1
        datePicker.setEnabled(false);
        assertEquals("-1", $(datePicker.getPickerRootElement()).attr("tabindex"));
    }

    public void testDateMinAndMax() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        final Date DATE_MIN = new Date(116, 2, 2);
        final Date DATE_MAX = new Date(116, 2, 20);
        datePicker.setDateMin(DATE_MIN);
        assertEquals(DATE_MIN, datePicker.getDateMin());
        datePicker.setDateMax(DATE_MAX);
        assertEquals(DATE_MAX, datePicker.getDateMax());
    }

    public void testDateValue() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        final Date DATE = new Date(116, 2, 5);
        final Date SECOND_DATE = new Date(116, 1, 0);

        boolean[] isValueChanged = {false};
        datePicker.addValueChangeHandler(event -> isValueChanged[0] = true);

        datePicker.setDate(DATE);
        assertEquals(DATE, datePicker.getDate());

        assertEquals(DATE, datePicker.getValue());

        datePicker.setEnabled(true);

        datePicker.setValue(DATE);
        assertEquals(datePicker.getValue(), DATE);

        datePicker.setValue(DATE);
        datePicker.setValue(DATE, false);
        assertEquals(DATE, datePicker.getValue());
        assertFalse(isValueChanged[0]);

        datePicker.setValue(SECOND_DATE, true);
        assertTrue(isValueChanged[0]);
    }

    // TODO Need advance checking to make sure the language is injected
    public void testLanguage() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        final DatePickerLanguage LANGUAGE = DatePickerLanguage.DA;
        datePicker.setLanguage(LANGUAGE);
        assertEquals(LANGUAGE, datePicker.getLanguage());
    }

    public void testOrientation() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        JsMaterialElement element = $(datePicker.pickatizedDateInput).pickadate("picker");
        datePicker.setOrientation(Orientation.LANDSCAPE);
        assertEquals(Orientation.LANDSCAPE, datePicker.getOrientation());
        assertTrue(element.root.hasClass(Orientation.LANDSCAPE.getCssName()));
        datePicker.setOrientation(Orientation.PORTRAIT);
        assertEquals(Orientation.PORTRAIT, datePicker.getOrientation());
        assertTrue(element.root.hasClass(Orientation.PORTRAIT.getCssName()));
    }

    public void testSelectionType() {
        // given
        MaterialDatePicker datePicker = getWidget();

        // when / then
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR_MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.MONTH_DAY, datePicker.getSelectionType());
        datePicker.setSelectionType(MaterialDatePicker.MaterialDatePickerType.YEAR);
        assertEquals(MaterialDatePicker.MaterialDatePickerType.YEAR, datePicker.getSelectionType());
    }

    @Override
    public void testTabIndex() {
        MaterialDatePicker datePicker = getWidget();
        super.checkTabIndex(datePicker.getDateInput());
    }
}
