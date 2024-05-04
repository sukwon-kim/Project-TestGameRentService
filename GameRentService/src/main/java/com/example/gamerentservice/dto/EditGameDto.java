package com.example.gamerentservice.dto;

import com.example.gamerentservice.type.GamePlatform;
import com.example.gamerentservice.type.SoftwareDistribution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditGameDto {

    private GamePlatform gamePlatform;
    private SoftwareDistribution softwareDistribution;
}
