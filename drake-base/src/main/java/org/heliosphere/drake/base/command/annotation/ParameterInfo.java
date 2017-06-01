/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.command.annotation;

/**
 * Contains public information for a command parameter.
 * <hr>
 * <dl>
 * <dt>Date:</dt>
 * <dd>$Date: 2013-01-30 07:42:30 +0100 (Wed, 30 Jan 2013) $</dd>
 * <dt>Comitter:</dt>
 * <dd>$Author: Christophe Resse $</dd>
 * <dt>Revision:</dt>
 * <dd>$Revision: 148 $</dd>
 * </dl>
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class ParameterInfo implements IParameterInfo
{
    /**
     * Parameter name.
     */
    private String name;
    
    /**
     * Parameter description.
     */
    private String description;
    
    /**
     * Parameter position.
     */
    private int position;
    
    /**
     * Is parameter optional?
     */
    private boolean optional = false;
    
    /**
     * Creates a new parameter information.
     * <p>
     * @param name Parameter name.
     * @param optional Is parameter optional?
     * @param description Parameter description.
     * @param position Parameter position.
     */
    public ParameterInfo(final String name, final boolean optional, final String description, final int position)
    {
        this.name = name;
        this.optional = optional;
        this.description = description;
        this.position = position;
    }
    
    @Override
    public final String getName()
    {
        return name;
    }
    
    @Override
    public final String getDescription()
    {
        return description;
    }
    
    @Override
    public final int getPosition()
    {
        return position;
    }
    
    @Override
    public boolean isOptional()
    {
        return optional;
    }
}
