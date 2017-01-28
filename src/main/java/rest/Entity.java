package rest;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import vlc.common.to.EntityTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The Entity API which Endpoints will be exposing as REST service.
 */
@Api(
        name = "entity",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "vlc-server-storage",
                ownerName = "vlc-server-storage",
                packagePath = ""
        )
)
public class Entity {

    /**
     * POST method.
     *
     * @param entity entity to create
     * @return entity created
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "post",
            path = "entity",
            httpMethod = ApiMethod.HttpMethod.POST)
    public EntityTO post(EntityTO entity) throws ServiceException {
        return entity;
    }

    /**
     * GET method.
     *
     * @param id entity id to retrieve
     * @return entity retrieved
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "get",
            path = "entity/{id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public EntityTO get(@Named("id") Integer id) throws ServiceException {
        return null;
    }

    /**
     * PUT method.
     *
     * @param entity entity to update
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "put",
            path = "entity",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public void put(EntityTO entity) throws ServiceException {
    }

    /**
     * DELETE method.
     *
     * @param id entity id to delete
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "delete",
            path = "entity/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void delete(@Named("id") Integer id) throws ServiceException {

    }

    /**
     * LIST method. Equivalent of multiple GETs.
     *
     * @return all the entities
     * @throws ServiceException on malformed requests or back end error
     */
    @ApiMethod(
            name = "list",
            path = "entity",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<EntityTO> list() throws ServiceException {
        return new ArrayList<>();
    }
}
