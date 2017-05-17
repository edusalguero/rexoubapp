package com.edusalguero.rexoubador.domain.shared;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

@Embeddable
@MappedSuperclass
public class UniqueId implements Serializable {

    @Column(columnDefinition = "BINARY(16)", length = 16, updatable = false, nullable = false)
    protected byte[] id;

    public UniqueId() {
    }

    public UniqueId(byte[] id) {
        this.id = id;
    }

    public UniqueId(String id) {
        this(toByteArray(UUID.fromString(id)));
    }

    private static byte[] toByteArray(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits()); // order is important here!
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private static UUID toUUID(byte[] byteArray) {
        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (byteArray[i] & 0xff);
        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (byteArray[i] & 0xff);
        return new UUID(msb, lsb);
    }

    public String getId() {
        return toString();
    }

    public byte[] id() {
        return id;
    }

    @Override
    public String toString() {
        return toUUID(id).toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(id);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UniqueId other = (UniqueId) obj;
        return Arrays.equals(id, other.id);
    }
}