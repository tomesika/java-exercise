package xmlParsers;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="ProcessSettings")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxBHandler {
    @XmlElement(name="Algorithm")
    private String algorithm;

    @XmlElement(name="KeyPath")
    private String keyPath;

    @XmlElement(name="SourceDirectory")
    private String sourceDirectory;

    @XmlElementWrapper(name="SourceFileName")
    private String sourceFileName;

}


