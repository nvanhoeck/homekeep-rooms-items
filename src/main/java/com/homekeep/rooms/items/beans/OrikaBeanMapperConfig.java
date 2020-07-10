package com.homekeep.rooms.items.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.Map;

public class OrikaBeanMapperConfig extends ConfigurableMapper {
    private MapperFactory mapperFactory;
    private ApplicationContext applicationContext;

    @Autowired
    public OrikaBeanMapperConfig(ApplicationContext applicationContext) {
        // false = delay init  of ConfigurableMapper
        // because registering of convertors needs be done before init.
        super(false);
        this.applicationContext = applicationContext;
        // init ConfigurableMapper
        this.init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        this.mapperFactory = factory;
        // next line because of
        // http://stackoverflow.com/questions/29867292/orika-not-able-to-map-when-the-application-is-running-on-jetty
        this.mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        addCustomMapperAndConverters();
        addDateTimeMapper();
    }

    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        // customize the factoryBuilder as needed
        factoryBuilder.mapNulls(true).build();
    }

    private void addCustomMapperAndConverters() {
        final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        converters.values().forEach(this::addConverter);

        final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        mappers.values().forEach(this::addMapper);
    }

    private void addConverter(final Converter<?, ?> converter) {
        mapperFactory.getConverterFactory().registerConverter(converter);
    }

    private void addMapper(final Mapper<?, ?> mapper) {
        mapperFactory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .mapNulls(true)
                .mapNullsInReverse(true)
                .customize((Mapper) mapper)
                .register();
    }

    private void addDateTimeMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
