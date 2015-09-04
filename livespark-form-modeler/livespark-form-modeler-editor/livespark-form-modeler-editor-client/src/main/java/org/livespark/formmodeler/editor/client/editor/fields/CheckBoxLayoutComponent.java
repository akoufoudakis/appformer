/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livespark.formmodeler.editor.client.editor.fields;

import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.HelpBlock;
import com.google.gwt.user.client.ui.IsWidget;
import org.livespark.formmodeler.editor.client.resources.i18n.FieldProperties;
import org.livespark.formmodeler.editor.model.impl.basic.CheckBoxFieldDefinition;
import org.uberfire.ext.properties.editor.model.PropertyEditorCategory;
import org.uberfire.ext.properties.editor.model.PropertyEditorFieldInfo;
import org.uberfire.ext.properties.editor.model.PropertyEditorType;

import javax.enterprise.context.Dependent;

/**
 * Created by pefernan on 9/2/15.
 */
@Dependent
public class CheckBoxLayoutComponent extends FieldLayoutComponent<CheckBoxFieldDefinition> {

    public CheckBoxLayoutComponent() {
    }

    public CheckBoxLayoutComponent( String formId, CheckBoxFieldDefinition fieldDefinition ) {
        init( formId, fieldDefinition );
    }

    @Override
    public IsWidget generateWidget() {
        if (fieldDefinition == null) return null;

        ControlGroup group = new ControlGroup(  );
        CheckBox checkBox = new CheckBox(fieldDefinition.getLabel());
        checkBox.setEnabled( !fieldDefinition.getReadonly() );
        group.add( checkBox );
        group.add( new HelpBlock(  ) );
        return group;
    }

    @Override
    public PropertyEditorCategory generatePropertyEditorCategory() {
        PropertyEditorCategory fieldProperties = new PropertyEditorCategory( "General Properties" );
        fieldProperties.withField( new PropertyEditorFieldInfo( FieldProperties.INSTANCE.label(), String.valueOf( fieldDefinition.getLabel() ), PropertyEditorType.TEXT ) {
            @Override
            public void setCurrentStringValue( final String currentStringValue ) {
                super.setCurrentStringValue( currentStringValue );
                fieldDefinition.setLabel( currentStringValue );
            }
        } );
        fieldProperties.withField( new PropertyEditorFieldInfo( FieldProperties.INSTANCE.required(), String.valueOf( fieldDefinition.getRequired() ), PropertyEditorType.BOOLEAN ) {
            @Override
            public void setCurrentStringValue( final String currentStringValue ) {
                super.setCurrentStringValue( currentStringValue );
                fieldDefinition.setRequired( Boolean.valueOf( currentStringValue ) );
            }
        } );
        fieldProperties.withField( new PropertyEditorFieldInfo( FieldProperties.INSTANCE.readonly(), String.valueOf( fieldDefinition.getReadonly() ), PropertyEditorType.BOOLEAN ) {
            @Override
            public void setCurrentStringValue( final String currentStringValue ) {
                super.setCurrentStringValue( currentStringValue );
                fieldDefinition.setReadonly( Boolean.valueOf( currentStringValue ) );
            }
        } );
        return fieldProperties;
    }

    @Override
    public CheckBoxLayoutComponent newInstance( String formId, CheckBoxFieldDefinition fieldDefinition ) {
        return new CheckBoxLayoutComponent( formId, fieldDefinition );
    }

    @Override
    public String getSupportedFieldDefinition() {
        return CheckBoxFieldDefinition.class.getName();
    }
}
