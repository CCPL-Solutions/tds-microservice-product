package co.com.viveres.susy.microserviceproduct.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static co.com.viveres.susy.microserviceproduct.DummyMock.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.viveres.susy.microserviceproduct.dto.ContentOutputDto;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;

@ExtendWith(MockitoExtension.class)
class ContentTypeServiceImplTest {
	
	@Mock
	private IContentRepository contentRepository;
	
	@InjectMocks
	private ContentTypeServiceImpl service;
	
	@Test
	void findAllContentTest() {
		when(this.contentRepository.findAll()).thenReturn(contentEntityList());
		
		List<ContentOutputDto> contentOutputDtoListExpected = contentOutputDtoList();
		List<ContentOutputDto> contentOutputDtoListActual = this.service.findAllContent();
		
		verify(this.contentRepository).findAll();
		
		assertFalse(contentOutputDtoListActual.isEmpty());
		assertTrue(contentOutputDtoListActual.size() > 0);
		assertEquals(contentOutputDtoListExpected, contentOutputDtoListActual);
		
	}

}
